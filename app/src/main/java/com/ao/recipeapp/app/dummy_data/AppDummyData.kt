package com.ao.recipeapp.app.dummy_data


object AppDummyData {

    fun getRandomRecipeImage(value:Int):String{
        return "https://loremflickr.com/1080/720/food?random=${value}"
    }

    fun getRandomProfileImage(value:Int):String{
        return "https://loremflickr.com/1080/720/profile?random=${value}"
    }

    val categories = listOf("Kahvaltılıklar","Çorbalar","Ana Yemekler","Tatlılar","Salatalar")

    val searchCategories = listOf(
        "Kahvaltılıklar",
        "Çorbalar",
        "Ana Yemekler",
        "Tatlılar",
        "Salatalar",
        "Makarnalar",
        "Kebaplar",
        "Köfteler",
        "Tavuk Yemekleri",
        "Balıklar",
        "Zeytinyağlılar",
        "Vejetaryen Yemekler",
        "Çocuk Yemekleri",
        "Diyet Yemekleri",
        "Hamur İşleri",
        "Tatlılar",
        "Kurabiyeler",
        "Pastalar",
        "Kekler",
        "Börekler",
        "Poğaçalar",
        "Sütlü Tatlılar",
        "Şerbetli Tatlılar",
        "Meyveli Tatlılar",
        "Çikolatalı Tatlılar"
    )


}

fun Int.lorem():String{
    val _loremData = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
    return _loremData.substring(0,this)
}