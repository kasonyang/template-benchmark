package com.mitchellbosecke.benchmark;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;

import java.util.Map;
import site.kason.tempera.engine.Configuration;
import site.kason.tempera.engine.Engine;
import site.kason.tempera.engine.Template;
import site.kason.tempera.loader.ClasspathTemplateLoader;


public class Tempera extends BaseBenchmark {

  private Map<String, Object> context;
  private Template template;

  @Setup
  public void setup() throws Exception {
    ClasspathTemplateLoader tloader = new ClasspathTemplateLoader(new String[]{".tpr.html"});
    Configuration conf = new Configuration();
    conf.setTemplateLoader(tloader);
    Engine engine = new Engine(conf);
    template = engine.compile("templates.stocks");
    this.context = getContext();
  }

  @Benchmark
  public String benchmark() throws Exception {
    return template.render(context);
  }

}
