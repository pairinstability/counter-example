import chisel3._

class Counter extends Module {
    // use width of 4
    val io = IO(new Bundle {
        val reset = Input(Bool())
        val count = Output(UInt(4.W))
        val overflowed = Output(Bool())
    })

    val count = RegInit(0.U(4.W))
    val overflowed = RegInit(false.B)

    when (io.reset) {
        count := count + 1.U;
    }

    // overflow detection
    when(count === "b1111".U) {
        overflowed := true.B
    }

    io.count := count
    io.overflowed := overflowed
}

object Main extends App {
    println(getVerilogString(new Counter))
}
