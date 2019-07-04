package com.joancolmenerodev.brewdogbeers.base.networking

sealed class Failure {
    data class ServerError(val message: String) : Failure()

    abstract class FeatureFailure : Failure()
}