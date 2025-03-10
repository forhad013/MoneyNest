package com.greenrobotdev.moneynest

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.jupiter.api.Test

class AppKonsistTest {
  
    @Test
    fun `names of packages are always lowercase`() {
        Konsist
            .scopeFromProject()
            .packages
            .assertTrue { it.name.matches("^[a-z.]+\$".toRegex()) }
    }
    
}