package hw1

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class MajorityCircuitTester extends AnyFlatSpec with ChiselScalatestTester {
  behavior of "MajorityCircuit"
  it should "correctly pass tests on all 8 possible inputs" in {
    test(new MajorityCircuit) { dut =>
      println("Testing a=0, b=0, c=0")
      dut.io.a.poke(0.B)
      dut.io.b.poke(0.B)
      dut.io.c.poke(0.B)
      dut.io.out.expect(0.U)

      println("Testing a=0, b=0, c=1")
      dut.io.a.poke(0.B)
      dut.io.b.poke(0.B)
      dut.io.c.poke(1.B)
      dut.io.out.expect(0.U)

      println("Testing a=0, b=1, c=0")
      dut.io.a.poke(0.B)
      dut.io.b.poke(1.B)
      dut.io.c.poke(0.B)
      dut.io.out.expect(0.U)

      println("Testing a=0, b=1, c=1")
      dut.io.a.poke(0.B)
      dut.io.b.poke(1.B)
      dut.io.c.poke(1.B)
      dut.io.out.expect(1.U)

      println("Testing a=1, b=0, c=0")
      dut.io.a.poke(1.B)
      dut.io.b.poke(0.B)
      dut.io.c.poke(0.B)
      dut.io.out.expect(0.U)

      println("Testing a=1, b=0, c=1")
      dut.io.a.poke(1.B)
      dut.io.b.poke(0.B)
      dut.io.c.poke(1.B)
      dut.io.out.expect(1.U)

      println("Testing a=1, b=1, c=0")
      dut.io.a.poke(1.B)
      dut.io.b.poke(1.B)
      dut.io.c.poke(0.B)
      dut.io.out.expect(1.U)

      println("Testing a=1, b=1, c=1")
      dut.io.a.poke(1.B)
      dut.io.b.poke(1.B)
      dut.io.c.poke(1.B)
      dut.io.out.expect(1.U)
    }
  }
}
