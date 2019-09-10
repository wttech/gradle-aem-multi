import com.intuit.karate.junit5.Karate

class HomeTest {

    @Karate.Test
    fun home() = Karate().feature("classpath:home.feature");

}
