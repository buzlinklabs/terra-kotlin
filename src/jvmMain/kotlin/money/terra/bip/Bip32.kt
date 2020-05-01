package money.terra.bip

import org.web3j.crypto.Bip32ECKeyPair

actual object Bip32 {

    actual fun keyPairFrom(seed: ByteArray, hdPath: IntArray): Bip32KeyPair {
        val masterKey = Bip32ECKeyPair.generateKeyPair(seed)
        val terraHD = Bip32ECKeyPair.deriveKeyPair(masterKey, hdPath)
        val privateKey = terraHD.privateKeyBytes33
        val publicKey = terraHD.publicKeyPoint.getEncoded(true)

        return Bip32KeyPair(publicKey, privateKey)
    }

    actual fun sign(messageHash: ByteArray, privateKey: ByteArray): ByteArray {
        val keyPair = Bip32ECKeyPair.create(privateKey)

        val signature = keyPair.sign(messageHash)
        val r = signature.r.toByteArray()
        val s = signature.s.toByteArray()

        var index = 0
        val start = if (r.size > 32) r.size - 32 else 0

        val result = ByteArray(r.size + s.size - start)

        for (i in start until r.size) {
            result[index++] = r[i]
        }

        for (i in s.indices) {
            result[index++] = s[i]
        }

        return result
    }
}