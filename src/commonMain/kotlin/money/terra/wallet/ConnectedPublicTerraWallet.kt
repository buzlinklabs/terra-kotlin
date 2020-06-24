package money.terra.wallet

import money.terra.Network
import money.terra.ProvidedNetwork
import money.terra.client.http.TerraHttpClient
import money.terra.model.Coin

open class ConnectedPublicTerraWallet(
    wallet: PublicTerraWallet,
    val httpClient: TerraHttpClient
) : PublicTerraWallet by wallet {

    val network: Network = httpClient.network

    var isConnected = false
        protected set

    private val bankApi = httpClient.bank()

    constructor(
        wallet: PublicTerraWallet,
        network: ProvidedNetwork
    ) : this(wallet, TerraHttpClient(network))

    open suspend fun connect() {
        isConnected = true
    }

    suspend fun getBalances(): List<Coin> = bankApi.getAccountBalances(address).result
}