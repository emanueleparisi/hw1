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
		val c0 = 7
    test(new PolyEval(c0, c0, c0)) { dut =>
      // Test `x = 0` and `enable = 0`, expected `out = 0`
      dut.io.enable.poke(false.B)
      dut.io.x.poke(0.U)
      dut.io.out.expect(0.U)

      // Test `x = 0` and `enable = 1`, expected `out = 7`
      dut.io.enable.poke(true.B)
      dut.io.x.poke(0.U)
      dut.io.out.expect(7.U)

      // Test `x = 9` and `enable = 1`, expected `out = 511`
      dut.io.enable.poke(true.B)
      dut.io.x.poke(9.U)
      dut.io.out.expect(637.U)
    }
  }

  behavior of "ComplexALU"
  it should "correctly calculate realOut onlyAdd=true" in {
    test(new ComplexALU(onlyAdder=true)) { dut =>
      // Test `n0 = 1+7i`, `n1 = 4-2i`, expected `realOut = 5`
      dut.io.real0.poke(1.S)
      dut.io.imag0.poke(7.S)
      dut.io.real1.poke(4.S)
      dut.io.imag1.poke(-2.S)
      dut.io.doAdd.poke(true.B)
      dut.io.realOut.expect(5.S)
    }
  }
  it should "correctly calculate realOut onlyAdd=false" in {
    test(new ComplexALU(onlyAdder = false)) { dut =>
			// Test `n0 = 1+7i`, `n1 = 4-2i`, `doAdd = true`, expected `realOut = 5`
      dut.io.real0.poke(1.S)
      dut.io.imag0.poke(7.S)
      dut.io.real1.poke(4.S)
      dut.io.imag1.poke(-2.S)
      dut.io.doAdd.poke(true.B)
      dut.io.realOut.expect(5.S)

      // Test `n0 = 1+7i`, `n1 = 4-2i`, `doAdd = false`, expected `realOut = -3`
      dut.io.real0.poke(1.S)
      dut.io.imag0.poke(7.S)
      dut.io.real1.poke(4.S)
      dut.io.imag1.poke(-2.S)
      dut.io.doAdd.poke(false.B)
      dut.io.realOut.expect(-3.S)
    }
  }
  it should "correctly calculate imagOut onlyAdd=true" in {
    test(new ComplexALU(onlyAdder = true)) { dut =>
      // Test `n0 = 1+7i`, `n1 = 4-2i`, expected `imagOut = 5`
      dut.io.real0.poke(1.S)
      dut.io.imag0.poke(7.S)
      dut.io.real1.poke(4.S)
      dut.io.imag1.poke(-2.S)
      dut.io.doAdd.poke(true.B)
      dut.io.imagOut.expect(5.S)
    }
  }
  it should "correctly calculate imagOut onlyAdd=false" in {
    test(new ComplexALU(onlyAdder = false)) { dut =>
			// Test `n0 = 1+7i`, `n1 = 4-2i`, `doAdd = true`, expected `imagOut = 5`
      dut.io.real0.poke(1.S)
      dut.io.imag0.poke(7.S)
      dut.io.real1.poke(4.S)
      dut.io.imag1.poke(-2.S)
      dut.io.doAdd.poke(true.B)
      dut.io.imagOut.expect(5.S)

      // Test `n0 = 1+7i`, `n1 = 4-2i`, `doAdd = false`, expected `imagOut = 9`
      dut.io.real0.poke(1.S)
      dut.io.imag0.poke(7.S)
      dut.io.real1.poke(4.S)
      dut.io.imag1.poke(-2.S)
      dut.io.doAdd.poke(false.B)
      dut.io.imagOut.expect(9.S)
    }
  }
}
