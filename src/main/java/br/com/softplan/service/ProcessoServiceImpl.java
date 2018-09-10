package br.com.softplan.service;

import br.com.softplan.dao.ProcessoDao;
import br.com.softplan.domain.Processo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProcessoServiceImpl implements ProcessoService{


    @Autowired
    private ProcessoDao processoDao;


    @Override
    public void salvar(Processo processo) {

        processoDao.salvar(processo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Processo> recuperar() {

        return processoDao.recuperar();
    }

    @Override
    @Transactional(readOnly = true)
    public Processo recuperarPorId(long id) {

        return processoDao.recuperarPorId(id);
    }

    @Override
    public void atualizar(Processo processo) {
        processoDao.atualizar(processo);

    }

    @Override
    public void excluir(long id) {

        processoDao.excluir(id);
    }

}
