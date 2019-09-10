import com.intuit.karate.junit5.Karate

class KarateTest {

    @Karate.Test
    fun test() = Karate().feature(
      "classpath:home.feature"
    );

}
