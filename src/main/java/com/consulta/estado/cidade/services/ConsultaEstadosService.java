package com.consulta.estado.cidade.services;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.consulta.estado.cidade.models.UF;
import com.consulta.estado.cidade.utils.Log;

@Service
public class ConsultaEstadosService {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${consulta.estados.url}")
	private String url;

	public List<UF> getEstados() {
		
		Log.getLog().info("Inicio obter Estados");
		
		UF[] ufs = restTemplate.getForObject(url, UF[].class);
		
		Log.getLog().info("Fim obter Estados :" + ufs);
		
		return Arrays.asList(ufs);
	}
}
