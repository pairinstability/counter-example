import org.scalatest.freespec.AnyFreeSpec
import chiseltest._

class CounterTests extends AnyFreeSpec with ChiselScalatestTester {
    "unit tests" in {
        test(new Counter) { c => 
            c.io.reset.poke(true)
            // expect 0 initially
            c.io.count.expect(0)
            // increment and expect 1
            c.clock.step()
            c.io.count.expect(1)
            // increment 3 and expect 4
            c.clock.step(3)
            c.io.count.expect(4)
            // expect no overflow
            c.io.overflowed.expect(false)
            // increment to max (15)
            c.clock.step(11)
            // disable then increment to max+1, should overflow
            c.io.reset.poke(false)
            c.clock.step()
            c.io.overflowed.expect(true)
        }
    }
}
