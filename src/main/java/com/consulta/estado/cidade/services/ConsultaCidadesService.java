package com.consulta.estado.cidade.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.consulta.estado.cidade.models.Cidade;
import com.consulta.estado.cidade.models.UF;
import com.consulta.estado.cidade.utils.Log;

@Service
public class ConsultaCidadesService {

	@Autowired
	private ConsultaEstadosService consultaEstadosService;

	@Autowired
	private RestTemplate restTemplate;

	@Value("${consulta.cidades.url}")
	private String url;

	public List<Cidade> getCidades() {

		Log.getLog().info("Consultando cidades...");

		List<UF> estados = this.consultaEstadosService.getEstados();
		List<Cidade> cidades = getCidadesPorEstados(estados);

		Log.getLog().info("Consulta de cidades finalizado...");

		return cidades;
	}

	private List<Cidade> getCidadesPorEstados(List<UF> estados) {

		List<Cidade> listaCidades = new ArrayList<Cidade>();

		Log.getLog().info("Consulta de cidades para os estados: " + estados);

		String estadosIds = String.join("|", estados.stream().map(estado -> estado.getId().toString()).collect(Collectors.toList()));

		Map<String, String> param = new HashMap<String, String>();
		param.put("UF", estadosIds);

		Cidade[] cidades = restTemplate.getForObject(url, Cidade[].class, param);
		listaCidades.addAll(Arrays.asList(cidades));

		Log.getLog().info("Fim consulta de cidades para os estados: " + estados);
		Log.getLog().info("Cidades encontradas: " + listaCidades);

		return listaCidades;
	}
}
