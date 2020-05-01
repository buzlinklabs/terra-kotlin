package money.terra.client.http.api

import money.terra.client.http.TerraHttpClient
import money.terra.model.Coin
import money.terra.model.ResultWrapper
import money.terra.model.Transaction
import money.terra.model.TypeWrapper
import money.terra.model.bank.SendCoinRequest
import money.terra.transaction.message.SendMessage

class BankApi(
    private val client: TerraHttpClient
) {

    suspend fun getAccountBalances(address: String): ResultWrapper<List<Coin>> {
        return client.get("/bank/balances/$address")
    }

    suspend fun generateSendCoinsTransaction(
        address: String,
        body: SendCoinRequest
    ): TypeWrapper<Transaction<SendMessage>> {
        return client.post("/bank/accounts/$address/transfers", body)
    }
}