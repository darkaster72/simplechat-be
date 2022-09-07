package me.iankit.simplechat.chat

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class GroupService(private val groupDao: GroupDao) {
    fun createGroup(dto: CreateGroupDto): Mono<Group> {
        return groupDao.save(dto.toEntity())
    }

    fun getGroupById(groupId: String): Mono<Group> {
        return groupDao.findById(groupId)
    }

    fun allGroups(): Flux<Group> {
        return groupDao.findAll()
    }
}