package hamusutax.core.collections

operator fun <K1, K2, V> Map<K1, Map<K2, V>>.get(key1: K1, key2: K2) =
    get(key1)?.get(key2)

operator fun <K1, K2, K3, V> Map<K1, Map<K2, Map<K3, V>>>.get(key1: K1, key2: K2, key3: K3) =
    get(key1)?.get(key2)?.get(key3)
