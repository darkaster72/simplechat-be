package me.iankit.simplechat.chat

import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface GroupDao : ReactiveMongoRepository<Group, String>