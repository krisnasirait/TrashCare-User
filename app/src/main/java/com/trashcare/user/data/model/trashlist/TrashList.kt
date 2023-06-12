package com.trashcare.user.data.model.trashlist


import com.trashcare.user.R


data class TrashList(
    val title: String,
    val description: String,
    val photo: Int,
    var count: Int,
)

val dummyTrashList = listOf(
    TrashList("Kertas/Kardus","Trash Care menerima berbagai macam kertas dan kardus. Pastikan sebelum dikirim, kertas dan kardus dalam keadaan bersih agar memudahkan proses daur ulang", R.drawable.cardboard_picture, 0),
    TrashList("Botol Plastik","Trash Care menerima berbagai macam botol plastik bekas. Pastikan sebelum dikirim, botol plastik dalam keadaan bersih agar memudahkan proses daur ulang", R.drawable.plastic_bottles_img, 0),
    TrashList("Botol Kaca","Trash Care menerima berbagai macam botol kaca bekas. Pastikan sebelum dikirim, botol kaca dalam keadaan bersih agar memudahkan proses daur ulang", R.drawable.glass_bottle_image, 0),
    TrashList("Kertas","Trash Care menerima berbagai macam kertas. Pastikan sebelum dikirim, kertas dalam keadaan bersih agar memudahkan proses daur ulang", R.drawable.paper_image, 0),
)
