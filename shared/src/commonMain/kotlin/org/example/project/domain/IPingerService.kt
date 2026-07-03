package org.example.project.domain

import org.example.project.model.Enterprise
import org.example.project.model.PingStatus

import org.example.project.model.Result

import javax.swing.Box

interface IPingerService {
    fun readJson() : List<Enterprise>
    suspend fun ping() : List<Result>
    suspend fun checkPing(ip: String) : PingStatus
}