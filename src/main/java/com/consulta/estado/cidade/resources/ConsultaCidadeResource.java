package com.consulta.estado.cidade.resources;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.consulta.estado.cidade.models.Cidade;
import com.consulta.estado.cidade.services.CidadesToMapService;
import com.consulta.estado.cidade.services.ConsultaCidadesService;
import com.consulta.estado.cidade.services.IFileConvertService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(value = "Consulta de Cidades - API")
public class ConsultaCidadeResource {

	@Autowired
	private IFileConvertService fileConvertCsv;

	@Autowired
	private ConsultaCidadesService consultaCidadesService;

	@Autowired
	private CidadesToMapService cidadesToMapService;

	@ApiOperation(value = "Retorna csv")
	@GetMapping("/cidades/csv")
	@ResponseStatus(HttpStatus.CREATED)
	public OutputStream getCsv(HttpServletResponse response) throws IOException {
		List<Map<String, String>> listaMapDados = cidadesToMapService.getMapResult();

		response.setContentType("text/csv");

		String nomeArquivo = "consulta_" + new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		response.setHeader("Content-Disposition", "attachment; filename=" + nomeArquivo + ".csv;");
		
		return fileConvertCsv.converter(response.getOutputStream(), listaMapDados);
	}

	@ApiOperation(value = "Retorna json")
	@GetMapping("/cidades/json")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Map<String, String>>> getCidades() {
		List<Map<String, String>> listaMapDados = cidadesToMapService.getMapResult();
		return new ResponseEntity<List<Map<String, String>>>(listaMapDados, HttpStatus.OK);
	}

	@ApiOperation(value = "obtem cidade por nome")
	@GetMapping("/cidades/{nomeCidade}")
	@Cacheable("cidade")
	public ResponseEntity<?> getCidade(@PathVariable String nomeCidade) {
		Cidade cidade = consultaCidadesService.getCidades().stream()
				.filter(cid -> cid.getNome().equalsIgnoreCase(nomeCidade)).findFirst().orElse(null);

		if (cidade != null) {
			return new ResponseEntity<>(cidade.getId(), HttpStatus.OK);
		} else {

			Map<String, String> map = new HashMap<String, String>();
			map.put("error", "Não encontrado cidade com o nome: " + nomeCidade);

			return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
		}
	}
}
