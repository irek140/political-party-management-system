import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Article } from '../../model/Article';
import { ArticleService } from '../../services/article.service';

@Component({
  selector: 'app-article-list',
  templateUrl: './article-list.component.html',
  styleUrls: ['./article-list.component.css']
})
export class ArticleListComponent implements OnInit {

  articles: Observable<Article[]>;

  constructor(private articleService: ArticleService) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.articles = this.articleService.getArticlesList();
  }

}
