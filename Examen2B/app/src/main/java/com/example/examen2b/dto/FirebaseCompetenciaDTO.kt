package com.example.examen2b.dto

import android.os.Parcel
import android.os.Parcelable


class FirebaseCompetenciaDTO (
    var id: String? = "",
    var nombre_Competencia: String? = "",
    var precio: Double? = 0.0,
    var disponibilidad: String? = "",
    var fecha: String? = "",
    var cantidad: Int = 0,
    var latitud: Double? = 0.0,
    var longitud: Double? = 0.0,
    var idCompetidor: String? = "",
): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString(),
    ) {
    }

    override fun toString(): String {

        return "ID Competencia: ${id} -" +
                "ID Competidor: ${idCompetidor} -" +
                "Nombre: ${nombre_Competencia} -" +
                "Precio:  ${precio} -" +
                "Disponibilidad: ${disponibilidad} -" +
                "Fecha de Ingreso: ${fecha} -" +
                "Cantidad: ${cantidad} -" +
                "Latiud: ${latitud} -" +
                "Longitud ${longitud}"

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(nombre_Competencia)
        parcel.writeDouble(precio!!)
        parcel.writeString(disponibilidad)
        parcel.writeString(fecha)
        parcel.writeInt(cantidad)
        parcel.writeDouble(latitud!!)
        parcel.writeDouble(longitud!!)
        parcel.writeString(idCompetidor)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FirebaseCompetenciaDTO> {
        override fun createFromParcel(parcel: Parcel): FirebaseCompetenciaDTO{
            return FirebaseCompetenciaDTO(parcel)
        }

        override fun newArray(size: Int): Array<FirebaseCompetenciaDTO?> {
            return arrayOfNulls(size)
        }
    }

}