/**
 * THIS FILE IS AUTOGENERATED, DO NOT MODIFY. EDIT THE FILES IN templates/
 * AND RUN ./gradlew :codegen INSTEAD!
 */

package koma.internal.default.generated.ndarray

import koma.extensions.fill
import koma.ndarray.*

class DefaultShortNDArrayFactory: NumericalNDArrayFactory<Short> {
    override fun create(vararg lengths: Int,
                        filler: (IntArray) -> Short): NDArray<Short> {
        return DefaultShortNDArray(*lengths).also {
            it.fill{filler(it)}
        }
    }

    override fun zeros(vararg lengths: Int): NDArray<Short> {
        return DefaultShortNDArray(*lengths).fill {
            0.toShort()
        }
    }

    override fun ones(vararg lengths: Int): NDArray<Short> {
        return DefaultShortNDArray(*lengths).fill {
            1.toShort()
        }
    }

    override fun rand(vararg lengths: Int): NDArray<Short> {
        return DefaultShortNDArray(*lengths).fill {
            0.toShort()
        }
    }

    override fun randn(vararg lengths: Int): NDArray<Short> {
        return DefaultShortNDArray(*lengths).fill {
            koma.internal.getRng().nextDouble().toShort()
        }
    }

}
