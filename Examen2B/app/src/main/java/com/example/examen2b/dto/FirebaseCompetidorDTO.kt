package com.example.examen2b.dto
import android.os.Parcel
import android.os.Parcelable


class FirebaseCompetidorDTO (
    var id: String? ="",
    var nombre: String? ="",
    var apellido: String? = "",
    var edad: Int? = 0,
    var email: String? = "",
    var telefono: String? = "",
): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel?.readString(),
        parcel?.readString(),
        parcel?.readInt(),
        parcel?.readString(),
        parcel?.readString()
    ) {
    }

    override fun toString(): String {
        return "ID: ${id} -" +
                "Nombre: ${nombre} -" +
                "Apellido: ${apellido} -" +
                "Edad:  ${edad} -" +
                "Correo Electrónico: ${email} -" +
                "Teléfono: ${telefono} "

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel?.writeString(id)
        parcel?.writeString(nombre)
        parcel?.writeString(apellido)
        parcel?.writeInt(edad!!)
        parcel?.writeString(email)
        parcel?.writeString(telefono)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FirebaseCompetidorDTO> {
        override fun createFromParcel(parcel: Parcel): FirebaseCompetidorDTO {
            return FirebaseCompetidorDTO(parcel)
        }

        override fun newArray(size: Int): Array<FirebaseCompetidorDTO?> {
            return arrayOfNulls(size)
        }
    }

}
