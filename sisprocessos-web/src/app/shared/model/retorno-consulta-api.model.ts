export class RetornoConsultaApi<T> {
  content: Array<T> = [];
  size = 0; // Quantidade de registros por página
  number = 0; // Número da página começando por "0"
  totalElements = 0; // Total de registros encontrados
  totalPages: number; // Quantidade de páginas
  numberOfElements: number; // Quantidade de registros na página atual
  first: boolean; // Identifica se é a primeira página
  last: boolean; // Identifica se é a última página
  sort: SortModel;
  pageable: PageableModel;
}

class SortModel {
  sorted: Boolean;
  unsorted: Boolean;
}

class PageableModel {
  sort: SortModel;
  offset: number;
  pageSize: number;
  pageNumber: number;
  unpaged: boolean;
  paged: boolean;
}
