package br.com.softplan.service;

import br.com.softplan.dao.ProcessousuarioDao;
import br.com.softplan.domain.Processousuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProcessousuarioServiceImpl implements ProcessousuarioService {

    @Autowired
    private ProcessousuarioDao processousuarioDao;

    @Autowired
    private ProcessoService processoService;

    @Autowired
    private UsuariosService usuariosService;

    @Override
    public void salvar(Processousuario processousuario, long processoId, long usuarioId) {
        processousuario.setProcesso(processoService.recuperarPorId(processoId));
        processousuario.setUsuarios(usuariosService.recuperarPorId(usuarioId));
        processousuarioDao.salvar(processousuario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Processousuario> recuperarPorProcesso(long processoId) {
        return processousuarioDao.recuperarPorProcesso(processoId);
    }

    @Override
    @Transactional(readOnly = true)
    public Processousuario recuperarPorProcessoIdEUsuarioId(long processoId, long usuarioId) {
        return processousuarioDao.recuperarPorProcessoIdEUsuarioId(processoId, usuarioId);
    }

    @Override
    public void atualizar(Processousuario processousuario, long processoId, long usuarioId) {
        processousuario.setProcesso(processoService.recuperarPorId(processoId));
        processousuario.setUsuarios(usuariosService.recuperarPorId(usuarioId));
        processousuarioDao.atualizar(processousuario);
    }

    @Override
    public void excluir(long processoId, long usuarioId) {
        processousuarioDao.excluir(recuperarPorProcessoIdEUsuarioId(processoId, usuarioId).getId());
    }
}
