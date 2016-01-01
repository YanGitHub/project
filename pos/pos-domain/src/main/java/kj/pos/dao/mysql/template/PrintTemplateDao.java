package kj.pos.dao.mysql.template;


import kj.pos.entity.template.PrintTemplate;
import kj.pos.util.mybatis.MyBatisRepository;

import java.util.List;

@MyBatisRepository
public interface PrintTemplateDao{
	public List<PrintTemplate> getList(PrintTemplate printTemplate);
	public void create(PrintTemplate printTemplate);
	public void update(PrintTemplate printTemplate);
}