package com.example.Tarea01

import android.os.Parcel
import android.os.Parcelable

class CompetidorEntrenador (
    var cedula_comp: Long?,
    var id_competen_comp: Long?,
    var nombre_comp: String?,
    var apellido_comp: String?,
    var correo_comp:String?,
    var fechaNacimiento_comp: String?,
    var telefono_comp: String?,
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun toString(): String {
        return "${cedula_comp}  ${id_competen_comp} ${nombre_comp} ${apellido_comp} ${correo_comp} ${fechaNacimiento_comp} ${telefono_comp}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(cedula_comp)
        parcel.writeValue(id_competen_comp)
        parcel.writeString(nombre_comp)
        parcel.writeString(apellido_comp)
        parcel.writeString(correo_comp)
        parcel.writeString(fechaNacimiento_comp)
        parcel.writeString(telefono_comp)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CompetidorEntrenador> {
        override fun createFromParcel(parcel: Parcel): CompetidorEntrenador {
            return CompetidorEntrenador(parcel)
        }

        override fun newArray(size: Int): Array<CompetidorEntrenador?> {
            return arrayOfNulls(size)
        }
    }
}
