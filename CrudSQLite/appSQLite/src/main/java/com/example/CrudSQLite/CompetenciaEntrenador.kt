package com.example.Tarea01

import android.os.Parcel
import android.os.Parcelable

class CompetenciaEntrenador (
    var id_competen: Long?,
    var nombre_competen: String?,
    var ciudad_competen: String?,
    var correo_competen:String?,
    var telefono_competen: String?,
): Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id_competen)
        parcel.writeString(nombre_competen)
        parcel.writeString(ciudad_competen)
        parcel.writeString(correo_competen)
        parcel.writeString(telefono_competen)
    }

    override fun toString(): String {
        return "${id_competen} ${nombre_competen} ${ciudad_competen} ${correo_competen} ${telefono_competen}"
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CompetenciaEntrenador> {
        override fun createFromParcel(parcel: Parcel): CompetenciaEntrenador {
            return CompetenciaEntrenador(parcel)
        }

        override fun newArray(size: Int): Array<CompetenciaEntrenador?> {
            return arrayOfNulls(size)
        }
    }

}