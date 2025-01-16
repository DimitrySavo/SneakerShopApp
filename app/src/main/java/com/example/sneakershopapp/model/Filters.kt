package com.example.sneakershopapp.model

enum class SortOrder { NONE, ASCENDING, DESCENDING }

data class PriceRange(val min: Int, val max: Int)

data class Filters(
    val sortByPrice: SortOrder = SortOrder.NONE,
    val priceRange: PriceRange? = null,
    val sizes: List<String> = emptyList(),
    val brands: List<String> = emptyList(),
    val colors: List<String> = emptyList(),
    val tags: List<String> = emptyList(),
    val onlyInStock: Boolean = false
)

fun List<Shoe>.applyFilters(filters: Filters): List<Shoe> {
    return this
        // Фильтрация по брендам
        .filter { shoe ->
            filters.brands.isEmpty() || filters.brands.contains(shoe.brandName)
        }
        // Фильтрация по цветам
        .filter { shoe ->
            filters.colors.isEmpty() || filters.colors.contains(shoe.color)
        }
        // Фильтрация по тегам
        .filter { shoe ->
            filters.tags.isEmpty() || filters.tags.any { tag -> shoe.tags.contains(tag) }
        }
        // Фильтрация по ключам размеров
        .filter { shoe ->
            filters.sizes.isEmpty() || shoe.sizes.keys.any { key ->
                filters.sizes.contains(key)
            }
        }
        // Фильтрация по диапазону цен
        .filter { shoe ->
            filters.priceRange == null || shoe.price in filters.priceRange.min.toDouble()..filters.priceRange.max.toDouble()
        }
        // Фильтрация по наличию на складе
        .filter { shoe ->
            !filters.onlyInStock || shoe.inStock
        }
        // Сортировка по цене
        .let { filteredList ->
            when (filters.sortByPrice) {
                SortOrder.ASCENDING -> filteredList.sortedBy { it.price }
                SortOrder.DESCENDING -> filteredList.sortedByDescending { it.price }
                SortOrder.NONE -> filteredList
            }
        }
}