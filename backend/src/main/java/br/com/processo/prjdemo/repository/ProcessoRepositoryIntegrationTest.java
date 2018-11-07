//package br.com.processo.prjdemo.repository;
//
//import java.util.Date;
//import java.util.List;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import br.com.processo.prjdemo.model.Processo;
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//public class ProcessoRepositoryIntegrationTest {
//	
//	@Autowired
//    private TestEntityManager entityManager;
// 
//    @Autowired
//    private ProcessoRepository processoRepository;
//    
//    @Test
//    public void whenFindAll() {
//        //given
//    	Processo processo1 = new Processo();
//        processo1.setDescricao("processo numero 1");
//        entityManager.persist(processo1);
//        entityManager.flush();
//
//        Processo processo2 = new Processo();
//        processo2.setDescricao("processo 2");
//        entityManager.persist(processo2);
//        entityManager.flush();
//
//        //when
//        List<Processo> lstProcessos = processoRepository.findAll();
//
//        //then
//        assertThat(lstProcessos.size()).isEqualTo(9);
//        assertThat(lstProcessos.get(10)).isEqualTo(processo1);
//        assertThat(lstProcessos.get(8)).isEqualTo(processo2);
//    }
//
//    @Test
//    public void whenFindById() {
//        //given
//        Processo processo = new Processo();
//        processo.setDescricao("Processo de compra");
//        processo.setDataCriacao(new Date());
//        entityManager.persist(processo);
//        entityManager.flush();
//
//        //when
//        Processo testProcesso = processoRepository.findById(processo.getId());
//
//        //then
//        assertThat(testProcesso.getDescricao()).isEqualTo(processo.getDescricao());
//    }
//
//}
