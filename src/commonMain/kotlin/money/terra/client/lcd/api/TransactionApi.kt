package money.terra.client.lcd.api

import money.terra.client.HttpClient
import money.terra.model.ResultWrapper
import money.terra.model.TransactionQueryResult
import money.terra.model.transaction.*

class TransactionApi(
    private val client: HttpClient
) {

    suspend fun getByHash(hash: String): TransactionQueryResult {
        return client.get("/txs/$hash")
    }

    suspend fun broadcastSignedTransaction(body: BroadcastTransactionSyncRequest): BroadcastTransactionSyncResult {
        return client.post("/txs", body)
    }

    suspend fun broadcastSignedTransaction(body: BroadcastTransactionAsyncRequest): BroadcastTransactionAsyncResult {
        return client.post("/txs", body)
    }

    suspend fun broadcastSignedTransaction(body: BroadcastTransactionBlockRequest): BroadcastTransactionBlockResult {
        return client.post("/txs", body)
    }

    suspend fun estimateFeeAndGas(body: EstimateFeeRequest): ResultWrapper<EstimateFee> {
        return client.post("/txs/estimate_fee", body)
    }
}