/**
 * THIS FILE IS AUTOGENERATED, DO NOT MODIFY. EDIT THE FILES IN templates/
 * AND RUN ./gradlew :codegen INSTEAD!
 */

package koma.internal.default.generated.ndarray

import koma.extensions.fill
import koma.ndarray.*

class DefaultFloatNDArrayFactory: NumericalNDArrayFactory<Float> {
    override fun create(vararg lengths: Int,
                        filler: (IntArray) -> Float): NDArray<Float> {
        return DefaultFloatNDArray(*lengths).also {
            it.fill{filler(it)}
        }
    }

    override fun zeros(vararg lengths: Int): NDArray<Float> {
        return DefaultFloatNDArray(*lengths).fill {
            0.toFloat()
        }
    }

    override fun ones(vararg lengths: Int): NDArray<Float> {
        return DefaultFloatNDArray(*lengths).fill {
            1.toFloat()
        }
    }

    override fun rand(vararg lengths: Int): NDArray<Float> {
        return DefaultFloatNDArray(*lengths).fill {
            0.toFloat()
        }
    }

    override fun randn(vararg lengths: Int): NDArray<Float> {
        return DefaultFloatNDArray(*lengths).fill {
            koma.internal.getRng().nextDouble().toFloat()
        }
    }

}
