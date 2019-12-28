package co.penguinLab.common

interface ItemClickListener<ModelType> {
    fun onItemClick(
        viewId: Int,
        modelList: List<ModelType>,
        clickedItem: ModelType,
        position: Int
    )
}