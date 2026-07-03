package org.example.project.repository

import kotlinproject.shared.generated.resources.Res
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import org.example.project.domain.IPingerService
import org.example.project.model.Enterprise
import org.example.project.model.PingStatus
import org.example.project.model.Result
import java.io.File
import java.nio.charset.Charset

class PingerService : IPingerService {
    override fun readJson(): List<Enterprise> {
//        val file = File("../enterprises.json")
//        val enterprises = Json.Default.decodeFromString<List<Enterprise>>(file.readText())
//        return enterprises
        val jsonText = object {}.javaClass.getResource("/enterprises.json")?.readText()
            ?: throw IllegalStateException("Файл enterprises.json не найден в ресурсах")
        return Json.Default.decodeFromString<List<Enterprise>>(jsonText)
    }

    override suspend fun ping(): List<Result> = coroutineScope {
        val enterprises = readJson()
        val results = enterprises.map { enterprise ->
            enterprise.ip_addresses.map { ip ->
                async {
                    val status = checkPing(ip)
                    if (status.isAlive) {
                        Result.Success(enterprise, ip)
                    } else {
                        Result.Failure(enterprise, status.error ?: "Ping failed", ip)
                    }
                }
            }
        }
        results.flatten().awaitAll()

    }

    override suspend fun checkPing(ip: String): PingStatus = withContext(Dispatchers.IO) {
        val cleanIp = ip.substringBefore('/')
        val pingCount = 4
        val timeoutMs = 2000

        val process = ProcessBuilder(
            "ping", "-n", pingCount.toString(), "-w", timeoutMs.toString(), cleanIp
        ).start()

        val exitCode = process.waitFor()

        if (exitCode == 0) {
            PingStatus(isAlive = true)
        } else {
            PingStatus(isAlive = false, error = "Нет ответа (exit code $exitCode)")
        }
    }

}