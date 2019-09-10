import com.intuit.karate.junit5.Karate

class KarateTest {

  @Karate.Test
  fun testAll() = Karate().relativeTo(javaClass)

}
