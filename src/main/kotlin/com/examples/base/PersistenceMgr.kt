package com.examples.base

import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory
import javax.persistence.Persistence

object PersistenceMgr {

    private val emf: EntityManagerFactory = Persistence.createEntityManagerFactory("cis-db").apply {
        //TODO add factory configurations
    }

    fun getEntityManager(): EntityManager {
        return emf.createEntityManager()
    }

    fun getEMFactory():EntityManagerFactory{
        return emf
    }

    fun close() {
        emf.close()
    }
}