package money.terra.model

import money.terra.model.transaction.BroadcastTransactionLog

data class TransactionQueryResult(
    val height: Long,
    val txhash: String,
    val codespace: String?,
    val code: Int?,
    val data: String?,
    val rawLog: String,
    val logs: List<BroadcastTransactionLog>?,
    val gasWanted: String,
    val gasUsed: String,
    val tx: TypeWrapper<Transaction<*>>,
    val timestamp: String
)

val TransactionQueryResult.isSuccess: Boolean
    get() = code == null