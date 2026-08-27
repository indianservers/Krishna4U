package com.indianservers.krishna4u.domain.repository

import com.indianservers.krishna4u.domain.model.GitaChapter
import com.indianservers.krishna4u.domain.model.Teaching
import kotlinx.coroutines.flow.Flow

interface GitaRepository { fun chapters(): Flow<List<GitaChapter>> }
interface TeachingRepository { fun teachings(): Flow<List<Teaching>> }
interface JournalRepository { fun bookmarkedIds(): Flow<Set<String>> }
