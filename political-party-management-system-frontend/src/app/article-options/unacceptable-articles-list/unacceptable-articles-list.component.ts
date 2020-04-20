import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Article } from '../../model/Article';
import { ArticleService } from '../../services/article.service';

@Component({
  selector: 'app-unacceptable-articles-list',
  templateUrl: './unacceptable-articles-list.component.html',
  styleUrls: ['./unacceptable-articles-list.component.css']
})
export class UnacceptableArticlesListComponent implements OnInit {


  unacceptableArticles: Observable<Article[]>;

  constructor(private articleService: ArticleService) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.unacceptableArticles = this.articleService.getUnacceptableArticlesList();
  }
}
