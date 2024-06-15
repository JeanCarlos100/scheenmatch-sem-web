package br.com.alura.scheenmatch.service;

public interface IConverteDados {
   <T> T obterDados(String json, Class<T> tClass);
}
