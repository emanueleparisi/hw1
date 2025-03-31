package hw1

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class HW1Tester extends AnyFlatSpec with ChiselScalatestTester {
  behavior of "RiscvITypeDecoder"
  it should "correctly decode instructions" in {
    test(new RiscvITypeDecoder) { c =>
      // Test `addi x3, x5, 1`
      c.io.instWord.poke("b000000000001_00101_000_00011_0010011".U)
			c.io.opcode.expect("b0010011".U)
      c.io.funct3.expect(0.U)
      c.io.rs1.expect(5.U)
      c.io.rd.expect(3.U)
      c.io.immSignExtended.expect(1.U)

      // Test `addi x3, x5, -1`
      c.io.instWord.poke("b111111111111_00101_000_00011_0010011".U)
			c.io.opcode.expect("b0010011".U)
      c.io.funct3.expect(0.U)
      c.io.rs1.expect(5.U)
      c.io.rd.expect(3.U)
      c.io.immSignExtended.expect("hffffffff".U)
    }
  }

  // See src/test/scala/hw1/MajorityCircuitTester.scala for Problem2

  behavior of "PolyEval"
  it should "correctly calculate out" in {
		val c0 = ???
    test(new PolyEval(c0, c0, c0)) { dut =>
			???
    }
  }

  behavior of "ComplexALU"
  it should "correctly calculate realOut onlyAdd=true" in {
    test(new ComplexALU(onlyAdder=true)) { dut =>
			???
    }
  }
  it should "correctly calculate realOut onlyAdd=false" in {
    test(new ComplexALU(onlyAdder = false)) { dut =>
			???
    }
  }
  it should "correctly calculate imagOut onlyAdd=true" in {
    test(new ComplexALU(onlyAdder = true)) { dut =>
			???
    }
  }
  it should "correctly calculate imagOut onlyAdd=false" in {
    test(new ComplexALU(onlyAdder = false)) { dut =>
			???
    }
  }
}
