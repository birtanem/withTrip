package place.action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.action.Action;
import common.vo.ActionForward;

public class ImageCallbackAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String realFolder = "";
		String saveFolder = "/placeUpload";
		int fileSize = 10 * 1024 * 1024;
		String fileName = "";

		ServletContext context = request.getServletContext(); // WAS 객체 가져와서 실제 폴더위치 찾기
		realFolder = context.getRealPath(saveFolder); // 가상폴더를 기준으로 실제경로 알아냄

		MultipartRequest multi = new MultipartRequest(request, realFolder, fileSize, "UTF-8", // 여기서 객체 생성시, 파일 저장됨.
				new DefaultFileRenamePolicy());

		fileName = multi.getOriginalFileName((String) multi.getFileNames().nextElement());

		String url = "." + saveFolder + "/" + fileName;
		JSONObject jsonO = new JSONObject();
		jsonO.put("url", url);
		jsonO.put("fileName", fileName);

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(jsonO.toJSONString());

		return null;
	}

}
