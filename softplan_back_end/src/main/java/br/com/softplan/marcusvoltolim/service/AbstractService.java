package br.com.softplan.marcusvoltolim.service;

import br.com.softplan.marcusvoltolim.model.support.EntityLongId;
import br.com.softplan.marcusvoltolim.repository.EntityRepository;
import br.com.softplan.marcusvoltolim.utils.JsonUtils;
import org.springframework.util.Assert;
import java.util.List;

public abstract class AbstractService<T extends EntityLongId> {
	
	private final EntityRepository<T> repository;
	private Class<T> classEntity;
	
	AbstractService(EntityRepository<T> repository, Class<T> classEntity) {
		Assert.notNull(repository, "repository obrigatorio");
		Assert.notNull(classEntity, "classEntity obrigatorio");
		this.repository = repository;
		this.classEntity = classEntity;
	}
	
	public List<T> findAll() {
		return repository.findAll();
	}
	
	public abstract List<T> findAllBy(String filtro);
	
	T entityFromJson(String json) {
		return JsonUtils.fromJson(json, classEntity);
	}
	
	public T create(String json) {
		T novaEntidade = entityFromJson(json);
		return repository.saveAndFlush(novaEntidade);
	}
	
	abstract void updateAtributos(T entidadePraAtualizar, String dadosAtualizadosJson);
	
	public T update(Long id, String json) {
		if (!repository.exists(id)) {
			return null;
		}
		
		T entidade = repository.getOne(id);
		updateAtributos(entidade, json);
		return repository.saveAndFlush(entidade);
	}
	
	public void delete(Long id) {
		repository.delete(id);
	}
	
}
