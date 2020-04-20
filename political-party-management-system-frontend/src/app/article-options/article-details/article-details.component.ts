import { Component, OnInit, Input } from '@angular/core';
import { Article } from '../../model/Article';
import { ArticleListComponent } from '../article-list/article-list.component';
import { ArticleService } from '../../services/article.service';

@Component({
  selector: 'app-article-details',
  templateUrl: './article-details.component.html',
  styleUrls: ['./article-details.component.css']
})
export class ArticleDetailsComponent implements OnInit {

  @Input()
  article: Article;

  markup: string;
  parser = new DOMParser();
  elements: Document;

  constructor(private articleService: ArticleService, private articlesList: ArticleListComponent) { }

  ngOnInit() {
    this.markup = this.article.content;
    this.elements = this.parser.parseFromString(this.markup, 'text/xml');
  }

}
