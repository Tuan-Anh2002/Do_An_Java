package Hieu_Thuoc_API;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.google.gson.Gson;

import Entity.QuanTri;
import Hieu_Thuoc_DAO.IQuanTri;
import Hieu_Thuoc_DAO.IplmQuanTri;

@Path("/quantri")
public class QuanTri_API {
	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String helo() {
		Gson gson=new Gson();
		String qt="helo";
		String json=gson.toJson(qt);
		return json;
	}
	@Path("/login/{tk}/{mk}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String Login(@PathParam(value = "tk")String TK,@PathParam(value = "mk")String MK) {
		IQuanTri iqt=new IplmQuanTri();
		QuanTri qt=iqt.Select(TK, MK);
		Gson gson=new Gson();
		String json=gson.toJson(qt);
		return json;
	}
}
