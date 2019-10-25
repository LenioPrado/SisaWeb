/*
 * File:	     Bibliography.java
 * Creation date: 11/06/2015
 * Author:        PagodeiroDoMal
 */
package br.edu.pcs.ifsulmg.sisa.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class Bibliography.
 */
public class Bibliography implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4041643678552209145L;

	/**
	 * Instantiates a new bibliography.
	 */
	public Bibliography() {

	}
	
	public String getFormattedText(){
		return String.format("Edição: %s. Editora: %s, %s.", _edition, _publisher, _year);
	}
	
	public String getTitleFormatted(){
		String title = _title;
		String isBasic = _isBasicView ? "Básica" : "Complementar";
		if (_showTitleFormatted) {
			title += " - " + isBasic;
		}
		return title;
	}

	/**
	 * Instantiates a new bibliography.
	 *
	 * @param bibliographyId the bibliography id
	 * @param bibliographyType the bibliography type
	 * @param title the title
	 * @param subtitle the subtitle
	 * @param edition the edition
	 * @param cityPlace the city place
	 * @param publisher the publisher
	 * @param year the year
	 * @param pages the pages
	 * @param volume the volume
	 * @param series the series
	 * @param isbn the isbn
	 */
	public Bibliography(int bibliographyId, int bibliographyType, String title, String subtitle,
			Integer edition, String cityPlace, String publisher, Integer year,
			Integer pages, Integer volume, String series, String isbn) {
		_bibliographyId = bibliographyId;
		_bibliographyType = bibliographyType;
		_title = title;
		_subtitle = subtitle;
		_edition = edition;
		_cityPlace = cityPlace;
		_publisher = publisher;
		_year = year;
		_pages = pages;
		_volume = volume;
		_series = series;
		_isbn = isbn;		
	}

	/** The _bibliography id. */
	private int _bibliographyId;
	
	/** The _bibliography type. */
	private int _bibliographyType;
	
	/** The _title. */
	private String _title;
	
	/** The _subtitle. */
	private String _subtitle;
	
	/** The _edition. */
	private Integer _edition;
	
	/** The _city place. */
	private String _cityPlace;
	
	/** The _publisher. */
	private String _publisher;
	
	/** The _year. */
	private Integer _year;
	
	/** The _pages. */
	private Integer _pages;
	
	/** The _volume. */
	private Integer _volume;
	
	/** The _series. */
	private String _series;
	
	/** The _isbn. */
	private String _isbn;
	
	/** The _is basic view. */
	private boolean _isBasicView;
	
	/** The _show title formatted. */
	private boolean _showTitleFormatted = false;

	/** The _authors. */
	private List<Author> _authors = new ArrayList<Author>();
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + _bibliographyId;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bibliography other = (Bibliography) obj;
		if (_bibliographyId != other._bibliographyId)
			return false;
		return true;
	}

	/**
	 * Gets the bibliography id.
	 *
	 * @return the bibliography id
	 */
	public int getBibliographyId() {
		return _bibliographyId;
	}

	/**
	 * Sets the bibliography id.
	 *
	 * @param bibliographyId the new bibliography id
	 */
	public void setBibliographyId(int bibliographyId) {
		_bibliographyId = bibliographyId;
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return _title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title) {
		_title = title;
	}

	/**
	 * Gets the subtitle.
	 *
	 * @return the subtitle
	 */
	public String getSubtitle() {
		return _subtitle;
	}

	/**
	 * Sets the subtitle.
	 *
	 * @param subtitle the new subtitle
	 */
	public void setSubtitle(String subtitle) {
		_subtitle = subtitle;
	}

	/**
	 * Gets the edition.
	 *
	 * @return the edition
	 */
	public Integer getEdition() {
		return _edition;
	}

	/**
	 * Sets the edition.
	 *
	 * @param edition the new edition
	 */
	public void setEdition(Integer edition) {
		_edition = edition;
	}

	/**
	 * Gets the city place.
	 *
	 * @return the city place
	 */
	public String getCityPlace() {
		return _cityPlace;
	}

	/**
	 * Sets the city place.
	 *
	 * @param cityPlace the new city place
	 */
	public void setCityPlace(String cityPlace) {
		_cityPlace = cityPlace;
	}

	/**
	 * Gets the publisher.
	 *
	 * @return the publisher
	 */
	public String getPublisher() {
		return _publisher;
	}

	/**
	 * Sets the publisher.
	 *
	 * @param publisher the new publisher
	 */
	public void setPublisher(String publisher) {
		_publisher = publisher;
	}

	/**
	 * Gets the year.
	 *
	 * @return the year
	 */
	public Integer getYear() {
		return _year;
	}

	/**
	 * Sets the year.
	 *
	 * @param year the new year
	 */
	public void setYear(Integer year) {
		_year = year;
	}

	/**
	 * Gets the pages.
	 *
	 * @return the pages
	 */
	public Integer getPages() {
		return _pages;
	}

	/**
	 * Sets the pages.
	 *
	 * @param pages the new pages
	 */
	public void setPages(Integer pages) {
		_pages = pages;
	}

	/**
	 * Gets the volume.
	 *
	 * @return the volume
	 */
	public Integer getVolume() {
		return _volume;
	}

	/**
	 * Sets the volume.
	 *
	 * @param volume the new volume
	 */
	public void setVolume(Integer volume) {
		_volume = volume;
	}

	/**
	 * Gets the series.
	 *
	 * @return the series
	 */
	public String getSeries() {
		return _series;
	}

	/**
	 * Sets the series.
	 *
	 * @param series the new series
	 */
	public void setSeries(String series) {
		_series = series;
	}

	public List<Author> getAuthors() {
		return _authors;
	}

	public void setAuthors(List<Author> authors) {
		_authors = authors;
	}

	public int getBibliographyType() {
		return _bibliographyType;
	}

	public void setBibliographyType(int bibliographyType) {
		_bibliographyType = bibliographyType;
	}

	public String getIsbn() {
		return _isbn;
	}

	public void setIsbn(String isbn) {
		_isbn = isbn;
	}

	public boolean isBasicView() {
		return _isBasicView;
	}

	public void setBasicView(boolean isBasicView) {
		_isBasicView = isBasicView;
	}

	public boolean isShowTitleFormatted() {
		return _showTitleFormatted;
	}

	public void setShowTitleFormatted(boolean showTitleFormatted) {
		_showTitleFormatted = showTitleFormatted;
	}
}