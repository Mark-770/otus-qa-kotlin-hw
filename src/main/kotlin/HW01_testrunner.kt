import kotlin.reflect.KFunction
import  kotlin.reflect.full.declaredFunctions


class TestFunV2 {

    interface TestRunner {
        fun <T> runTest(steps: T, test: () -> Unit)
    }

    fun after() {
        println("After method called")
    }

    fun afterAll() {
        println("After method called")
    }
    fun ALLafter() {
        println("ALL After method called")
    }

    fun testMethod() {
        println("Test method called")
    }

    fun afterMethod() {
        println("After method called")
    }

    fun before() {
        println("before method called")
    }

    fun before242() {
        println("before method called")
    }
    fun b242before() {
        println("b242before method called")
    }

    fun beforeTest() {
        println("before method called")
    }
}

class HM01_testrunner : TestFunV2.TestRunner {

    private val beforeFun = Regex("before\\w*")
    private val afterFun = Regex("after\\w*")
    private val testFun = Regex("test\\w*")
    override fun <T> runTest(steps: T, test: () -> Unit) {
        val functions: Collection<KFunction<*>> = TestFunV2::class.declaredFunctions
        for (function in functions) {
            if (function.name.contains(beforeFun)) {
                println("Before method ${function.name}")
            }
        }
        for (function in functions) {
            if (function.name.contains(testFun)) {
                println("Start tests")
            }
        }
        for (function in functions) {
            if (function.name.contains(afterFun)) {
                println("After method called ${function.name}")
            }
        }
    }
}

fun main() {
    val myRunner = HM01_testrunner()
    val testFun2 = TestFunV2()

    myRunner.runTest(steps = testFun2)
    { testFun2.after() }
}