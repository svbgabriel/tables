package br.svbgabriel.tables.config

import java.io.File

class UtilConfig {

    companion object {
        @JvmStatic
        fun readConfig(path: String): String = File(path).readText(Charsets.UTF_8)
    }
}
