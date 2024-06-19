import com.example.androidapp.kotlinegs.LoginManager
import junit.framework.TestCase
import org.junit.Assert.assertEquals



class LoginManagerTest : TestCase() {

    private var loginManager: LoginManager? = null

    @Throws(Exception::class)
    override fun setUp() {
        super.setUp()
        loginManager = LoginManager()
    }

    fun testValidLogin() {
        val isValid = loginManager?.isValidLogin("validUser", "correctPassword")
        assertEquals(true, isValid)
    }

    fun testInvalidLogin() {
        val isValid = loginManager?.isValidLogin("wrongUser", "incorrectPassword")
        assertEquals(false, isValid)
    }

    @Throws(Exception::class)
    override fun tearDown() {
        super.tearDown()
        loginManager = null
    }
}