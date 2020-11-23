package fabricadesoftware.com.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Example {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("user_id")
    @Expose
    public Integer userId;
    @SerializedName("name_user")
    @Expose
    public String nameUser;
    @SerializedName("surname_user")
    @Expose
    public String surnameUser;
    @SerializedName("cedula_user")
    @Expose
    public String cedulaUser;
    @SerializedName("ciudad")
    @Expose
    public String ciudad;
    @SerializedName("bloque")
    @Expose
    public String bloque;
    @SerializedName("direccion")
    @Expose
    public String direccion;
    @SerializedName("estado")
    @Expose
    public String estado;
    @SerializedName("nombre_activo")
    @Expose
    public Object nombreActivo;
    @SerializedName("serial")
    @Expose
    public Object serial;
    @SerializedName("placa")
    @Expose
    public Object placa;
    @SerializedName("salon")
    @Expose
    public String salon;
    @SerializedName("programa")
    @Expose
    public String programa;
    @SerializedName("celular")
    @Expose
    public String celular;
    @SerializedName("referencia")
    @Expose
    public String referencia;
    @SerializedName("cantidad")
    @Expose
    public Integer cantidad;
    @SerializedName("salida_por")
    @Expose
    public Object salidaPor;
    @SerializedName("descripcion")
    @Expose
    public String descripcion;
    @SerializedName("editado_por")
    @Expose
    public String editadoPor;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;

}
