package com.wnsilveira.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.wnsilveira.cursomc.domain.Categoria;
import com.wnsilveira.cursomc.domain.Cidade;
import com.wnsilveira.cursomc.domain.Cliente;
import com.wnsilveira.cursomc.domain.Endereco;
import com.wnsilveira.cursomc.domain.Estado;
import com.wnsilveira.cursomc.domain.ItemPedido;
import com.wnsilveira.cursomc.domain.Pagamento;
import com.wnsilveira.cursomc.domain.PagamentoComBoleto;
import com.wnsilveira.cursomc.domain.PagamentoComCartao;
import com.wnsilveira.cursomc.domain.Pedido;
import com.wnsilveira.cursomc.domain.Produto;
import com.wnsilveira.cursomc.domain.enums.EstadoPagamento;
import com.wnsilveira.cursomc.domain.enums.Perfil;
import com.wnsilveira.cursomc.domain.enums.TipoCliente;
import com.wnsilveira.cursomc.repositories.CategoriaRepository;
import com.wnsilveira.cursomc.repositories.CidadeRepository;
import com.wnsilveira.cursomc.repositories.ClienteRepository;
import com.wnsilveira.cursomc.repositories.EnderecoRepository;
import com.wnsilveira.cursomc.repositories.EstadoRepository;
import com.wnsilveira.cursomc.repositories.ItemPedidoRepository;
import com.wnsilveira.cursomc.repositories.PagamentoRepository;
import com.wnsilveira.cursomc.repositories.PedidoRepository;
import com.wnsilveira.cursomc.repositories.ProdutoRepository;

@Service
public class DBService {
	
	@Autowired
	private BCryptPasswordEncoder pe;
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public void instantiateTestDatabase() throws ParseException {
		
		Categoria cat1 = new Categoria("Informática");
		Categoria cat2 = new Categoria("Escritório");
		Categoria cat3 = new Categoria("Cama, Mesa e Banho");
		Categoria cat4 = new Categoria("Eletrônicos");
		Categoria cat5 = new Categoria("Jardinagem");
		Categoria cat6 = new Categoria("Decoração");
		Categoria cat7 = new Categoria("Perfumaria");
		
		Produto p1 = new Produto("Computador", 2000.00);
		Produto p2 = new Produto("Impressora", 800.00);
		Produto p3 = new Produto("Mouse", 80.00);
		Produto p4 = new Produto("Mesa de escritório", 300.00);
		Produto p5 = new Produto("Toalha", 50.00);
		Produto p6 = new Produto("Colcha", 200.00);
		Produto p7 = new Produto("TV true color", 1200.00);
		Produto p8 = new Produto("Roçadeira", 800.00);
		Produto p9 = new Produto("Abajour", 100.00);
		Produto p10 = new Produto("Pendente", 180.00);
		Produto p11 = new Produto("Shampoo", 90.00);
		
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));
		
		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		
		Estado est1 = new Estado("Minas Gerais");
		Estado est2 = new Estado("São Paulo");
		Estado est3 = new Estado("Paraná");
		Estado est4 = new Estado("Santa Catarina");
		Estado est5 = new Estado("Rio Grande do Sul");
		Estado est6 = new Estado("Mato Grosso");
		Estado est7 = new Estado("Alagoas");
		Estado est8 = new Estado("Rio Grande do Norte");
		Estado est9 = new Estado("Mato Grosso do sul");
		Estado est10 = new Estado("Rio de Janeiro");
		
		Cidade c1 =  new Cidade("Uberlândia", est1);
		Cidade c2 = new Cidade("São Paulo", est2);
		Cidade c3 = new Cidade("Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2, est3, est4, est5, est6, est7, est8, est9, est10));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente("Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOA_FISICA, pe.encode("123"));
		cli1.getTelefones().addAll(Arrays.asList("3333-3333", "3636-3636"));

		Cliente cli2 = new Cliente("Ana Costa", "ana@gmail.com", "56957151727", TipoCliente.PESSOA_FISICA, pe.encode("123"));
		cli2.getTelefones().addAll(Arrays.asList("3333-4444", "8484-3636"));
		cli2.addPerfil(Perfil.ADMIN);
		
		Endereco e1 = new Endereco("Rua Flores", "300", "Apto 203", "Jardim", "86030-000", cli1, c1);
		Endereco e2 = new Endereco("Av Matos", "100", "Sala 800", "Centro", "86030-100", cli1, c2);
		Endereco e3 = new Endereco("Av Floriano", "2106", null, "Centro", "86027-100", cli2, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		cli2.getEnderecos().addAll(Arrays.asList(e3));
		
		clienteRepository.saveAll(Arrays.asList(cli1, cli2));
		enderecoRepository.saveAll(Arrays.asList(e1, e2, e3));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}
}
