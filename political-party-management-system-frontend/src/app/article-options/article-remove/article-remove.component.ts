import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Article } from 'src/app/model/Article';
import { ArticleService } from 'src/app/services/article.service';

@Component({
  selector: 'app-article-remove',
  templateUrl: './article-remove.component.html',
  styleUrls: ['./article-remove.component.css']
})
export class ArticleRemoveComponent implements OnInit {

  articles: Observable<Article[]>;

  constructor(private articleService: ArticleService) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.articles = this.articleService.getArticlesList();
  }

}
