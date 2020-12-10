package com.memoizr.assertk

import org.assertj.core.api.AbstractLongAssert
import org.assertj.core.api.Assertions

class LongAssert internal constructor(
        subjectUnderTest: Long?,
        override val assertion: AbstractLongAssert<*> = Assertions.assertThat(subjectUnderTest)) :
        AbstractAssertBuilder<LongAssert, Long>(subjectUnderTest, LongAssert::class.java) {

    infix fun isLessThan(expected: Long): LongAssert {
        assertion.isLessThan(expected)
        return this
    }

    infix fun isLessThanOrEqualTo(expected: Long): LongAssert {
        assertion.isLessThanOrEqualTo(expected)
        return this
    }

    infix fun isGreaterThan(expected: Long): LongAssert {
        assertion.isGreaterThan(expected)
        return this
    }

    infix fun isGreaterThanOrEqualTo(expected: Long): LongAssert {
        assertion.isGreaterThanOrEqualTo(expected)
        return this
    }

    infix fun isBetween(expected: ClosedRange<Long>): LongAssert {
        assertion.isBetween(expected.start, expected.endInclusive)
        return this
    }

    infix fun isStrictlyBetween(expected: ClosedRange<Long>): LongAssert {
        assertion.isStrictlyBetween(expected.start, expected.endInclusive)
        return this
    }

    infix fun isCloseTo(expected: Long): Close {
        return Close(expected, assertion, this)
    }

    infix fun _is(expected: NumberSelector): LongAssert {
        when (expected) {
            zero -> assertion.isZero()
            notZero -> assertion.isNotZero()
            positive -> assertion.isPositive()
            notPositive -> assertion.isNotPositive()
            negative -> assertion.isNegative()
            notNegative -> assertion.isNotNegative()
        }
        return this
    }

    class Close(private val actual: Long, private val assertion: AbstractLongAssert<*>, private val assert: LongAssert) {
        infix fun withinOffset(expected: Long): LongAssert {
            assertion.isCloseTo(actual, Assertions.within(expected))
            return assert
        }

        infix fun withinPercentage(expected: Number): LongAssert {
            assertion.isCloseTo(actual, Assertions.withinPercentage(expected.toDouble()))
            return assert
        }
    }
}

infix fun Long.isLessThan(expected: Long): LongAssert =
        expect that this isLessThan expected

infix fun Long.isLessThanOrEqualTo(expected: Long): LongAssert =
        expect that this isLessThanOrEqualTo expected

infix fun Long.isGreaterThan(expected: Long): LongAssert =
        expect that this isGreaterThan expected

infix fun Long.isGreaterThanOrEqualTo(expected: Long): LongAssert =
        expect that this isGreaterThanOrEqualTo expected

infix fun Long.isBetween(expected: ClosedRange<Long>): LongAssert =
        expect that this isBetween expected

infix fun Long.isStrictlyBetween(expected: ClosedRange<Long>): LongAssert =
        expect that this isStrictlyBetween expected

infix fun Long.isCloseTo(expected: Long): LongAssert.Close =
        expect that this isCloseTo expected

infix fun Long._is(expected: NumberSelector): LongAssert =
        expect that this _is expected
